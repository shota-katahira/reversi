const meta = document.createElement('meta');
meta.charset = 'utf-8';
document.head.appendChild(meta);

const title = document.createElement('title');
title.textContent = 'Reversi Game';
document.head.appendChild(title);

const link = document.createElement('link');
link.rel = 'stylesheet';
link.href = 'css/style.css';
document.head.appendChild(link);

const container = document.createElement('div');
container.id = 'container';
document.body.appendChild(container);

const h1 = document.createElement('h1');
h1.textContent = 'Reversi';
container.appendChild(h1);

const board = document.createElement('div');
board.id = 'board';
container.appendChild(board);

const table = document.createElement('table');
board.appendChild(table);

for (let i = 1; i <= 8; i++) {
  const tr = document.createElement('tr');
  table.appendChild(tr);
  
  for (let j = 1; j <= 8; j++) {
    const td = document.createElement('td');
    td.classList.add('cell');
    td.id = i.toString() + j.toString();
    tr.appendChild(td);
  }
}
