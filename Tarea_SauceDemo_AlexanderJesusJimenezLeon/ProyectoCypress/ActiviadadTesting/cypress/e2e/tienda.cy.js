describe('Prueba de compra en SauceDemo', () => {
  
  it('Debe iniciar sesión y agregar 4 productos al carrito', () => {
    
    cy.visit('https://www.saucedemo.com')

    cy.get('#user-name').type('standard_user')
    cy.get('#password').type('secret_sauce')
    cy.get('#login-button').click()

    cy.url().should('include', '/inventory.html')
    cy.log('Login exitoso')

    cy.get('.btn_inventory').each(($btn, index) => {
      if (index < 4) {
        cy.wrap($btn).click()
        cy.log(`Producto ${index + 1} agregado`)
      }
    })

    cy.get('.shopping_cart_badge')
      .should('be.visible')
      .and('have.text', '4')
      .then(($badge) => {
        console.log('RESULTADO FINAL: El carrito tiene ' + $badge.text() + ' artículos.')
        cy.log('PRUEBA EXITOSA: Se agregaron 4 artículos.')
      })
  })
})