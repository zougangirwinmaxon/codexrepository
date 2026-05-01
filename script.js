const canvas = document.getElementById('game');
const ctx = canvas.getContext('2d');
const scoreElement = document.getElementById('score');
const highScoreElement = document.getElementById('high-score');
const statusElement = document.getElementById('status');
const startButton = document.getElementById('start-btn');
const restartButton = document.getElementById('restart-btn');

const gridSize = 20;
const tileCount = canvas.width / gridSize;
const speedMs = 120;

let snake;
let direction;
let pendingDirection;
let food;
let score;
let highScore = Number(localStorage.getItem('snakeHighScore') || 0);
let gameTimer = null;
let isRunning = false;

highScoreElement.textContent = highScore;

function randomFoodPosition() {
  while (true) {
    const position = {
      x: Math.floor(Math.random() * tileCount),
      y: Math.floor(Math.random() * tileCount),
    };

    const hitSnake = snake.some((segment) => segment.x === position.x && segment.y === position.y);
    if (!hitSnake) {
      return position;
    }
  }
}

function resetGameState() {
  snake = [
    { x: 10, y: 10 },
    { x: 9, y: 10 },
    { x: 8, y: 10 },
  ];
  direction = { x: 1, y: 0 };
  pendingDirection = direction;
  food = randomFoodPosition();
  score = 0;
  scoreElement.textContent = score;
}

function drawBoard() {
  ctx.fillStyle = '#e2e8f0';
  ctx.fillRect(0, 0, canvas.width, canvas.height);

  for (let i = 0; i < tileCount; i += 1) {
    for (let j = 0; j < tileCount; j += 1) {
      ctx.fillStyle = (i + j) % 2 === 0 ? '#f8fafc' : '#f1f5f9';
      ctx.fillRect(i * gridSize, j * gridSize, gridSize, gridSize);
    }
  }
}

function drawSnake() {
  snake.forEach((segment, index) => {
    ctx.fillStyle = index === 0 ? '#16a34a' : '#22c55e';
    ctx.fillRect(segment.x * gridSize + 1, segment.y * gridSize + 1, gridSize - 2, gridSize - 2);
  });
}

function drawFood() {
  ctx.fillStyle = '#ef4444';
  ctx.beginPath();
  ctx.arc(
    food.x * gridSize + gridSize / 2,
    food.y * gridSize + gridSize / 2,
    gridSize / 2.6,
    0,
    Math.PI * 2,
  );
  ctx.fill();
}

function drawGame() {
  drawBoard();
  drawFood();
  drawSnake();
}

function setStatus(message, isError = false) {
  statusElement.textContent = message;
  statusElement.style.color = isError ? '#b91c1c' : '#0f766e';
}

function endGame() {
  isRunning = false;
  clearInterval(gameTimer);
  gameTimer = null;

  if (score > highScore) {
    highScore = score;
    highScoreElement.textContent = highScore;
    localStorage.setItem('snakeHighScore', String(highScore));
  }

  setStatus(`游戏结束！本次得分 ${score}，点击“重新开始”再来一局。`, true);
}

function step() {
  direction = pendingDirection;

  const newHead = {
    x: snake[0].x + direction.x,
    y: snake[0].y + direction.y,
  };

  const hitWall = newHead.x < 0 || newHead.x >= tileCount || newHead.y < 0 || newHead.y >= tileCount;
  const hitSelf = snake.some((segment) => segment.x === newHead.x && segment.y === newHead.y);

  if (hitWall || hitSelf) {
    endGame();
    return;
  }

  snake.unshift(newHead);

  if (newHead.x === food.x && newHead.y === food.y) {
    score += 1;
    scoreElement.textContent = score;
    food = randomFoodPosition();
    setStatus('吃到食物！继续加油！');
  } else {
    snake.pop();
  }

  drawGame();
}

function startGame() {
  if (isRunning) {
    return;
  }

  resetGameState();
  drawGame();
  isRunning = true;
  setStatus('游戏进行中...');
  gameTimer = setInterval(step, speedMs);
}

function restartGame() {
  if (gameTimer) {
    clearInterval(gameTimer);
    gameTimer = null;
  }
  isRunning = false;
  startGame();
}

window.addEventListener('keydown', (event) => {
  const keyDirectionMap = {
    ArrowUp: { x: 0, y: -1 },
    ArrowDown: { x: 0, y: 1 },
    ArrowLeft: { x: -1, y: 0 },
    ArrowRight: { x: 1, y: 0 },
  };

  const newDirection = keyDirectionMap[event.key];
  if (!newDirection || !isRunning) {
    return;
  }

  const isReverse =
    newDirection.x === -direction.x &&
    newDirection.y === -direction.y;

  if (!isReverse) {
    pendingDirection = newDirection;
  }
});

startButton.addEventListener('click', startGame);
restartButton.addEventListener('click', restartGame);

resetGameState();
drawGame();
