const cartService = {
    cartKey: 'verdeUrbanoCart',

    getCart: function() {
        const cart = localStorage.getItem(this.cartKey);
        return cart ? JSON.parse(cart) : [];
    },

    saveCart: function(cart) {
        localStorage.setItem(this.cartKey, JSON.stringify(cart));
    },

    addToCart: function(product) {
        const cart = this.getCart();
        const existingItem = cart.find(item => item.id === product.id);

        if (existingItem) {
            existingItem.quantity += 1;
        } else {
            cart.push({ ...product, quantity: 1 });
        }
        this.saveCart(cart);
    },

    removeFromCart: function(productId) {
        let cart = this.getCart();
        cart = cart.filter(item => item.id !== productId);
        this.saveCart(cart);
    },

    clearCart: function() {
        localStorage.removeItem(this.cartKey);
    }
}; 