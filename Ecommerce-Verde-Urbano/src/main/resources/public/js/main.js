async function carregarPlantas() {
    try {
        const response = await fetch('/api/plantas');
        const plantas = await response.json();
        
        const grid = document.querySelector('.plantas-grid');
        grid.innerHTML = ''; 
        
        plantas.forEach(planta => {
            const plantaElement = document.createElement('div');
            plantaElement.className = 'planta';
            plantaElement.innerHTML = `
                <img src="${planta.imagemUrl}" alt="${planta.nome}" class="planta-imagem">
                <h2>${planta.nome}</h2>
                <p class="preco">R$ ${planta.preco.toFixed(2)}</p>
                <p class="manutencao">Manutenção: ${planta.manutencao}</p>
            `;
            grid.appendChild(plantaElement);
        });
    } catch (error) {
        console.error('Erro ao carregar plantas:', error);
        alert('Erro ao carregar as plantas. Por favor, tente novamente.');
    }
}

async function enviarCadastro(event) {
    event.preventDefault();
    
    const form = event.target;
    const formData = new FormData(form);
    const data = Object.fromEntries(formData.entries());
    
    try {
        const response = await fetch('/api/cadastro', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        });
        
        const result = await response.json();
        
        if (response.ok) {
            alert(result.message);
            window.location.href = result.redirectUrl;
        } else {
            alert(result.error || 'Erro ao processar cadastro');
        }
    } catch (error) {
        console.error('Erro ao enviar cadastro:', error);
        alert('Erro ao enviar cadastro. Por favor, tente novamente.');
    }
}

function updateCartCount() {
    const cartCount = document.getElementById('cart-count');
    if (cartCount) {

        const count = localStorage.getItem('cartCount') || 0;
        cartCount.textContent = count;
    }
}

function addToCart(productId) {
    let cart = JSON.parse(localStorage.getItem('cart')) || [];
    cart.push(productId);
    localStorage.setItem('cart', JSON.stringify(cart));
    localStorage.setItem('cartCount', cart.length);
    updateCartCount();
    
    showNotification('Produto adicionado ao carrinho!');
}

function showNotification(message) {
    const notification = document.createElement('div');
    notification.className = 'notification';
    notification.textContent = message;
    
    document.body.appendChild(notification);

    setTimeout(() => {
        notification.classList.add('show');
    }, 100);

    setTimeout(() => {
        notification.classList.remove('show');
        setTimeout(() => {
            notification.remove();
        }, 300);
    }, 3000);
}

function toggleMobileMenu() {
    const navbar = document.querySelector('.navbar');
    navbar.classList.toggle('active');
}

document.addEventListener('DOMContentLoaded', () => {

    if (document.querySelector('.plantas-grid')) {
        carregarPlantas();
    }

    const cadastroForm = document.getElementById('cadastroForm');
    if (cadastroForm) {
        cadastroForm.addEventListener('submit', enviarCadastro);
    }
    
    updateCartCount();

    const menuButton = document.querySelector('.menu-button');
    if (menuButton) {
        menuButton.addEventListener('click', toggleMobileMenu);
    }

    const addToCartButtons = document.querySelectorAll('.add-to-cart');
    addToCartButtons.forEach(button => {
        button.addEventListener('click', (e) => {
            const productId = e.target.dataset.productId;
            addToCart(productId);
        });
    });
}); 