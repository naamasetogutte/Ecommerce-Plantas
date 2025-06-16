<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Pedido Concluído</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; background-color: #f4f4f4; color: #333; }
        .container { background: #fff; padding: 30px; border-radius: 8px; box-shadow: 0 2px 4px rgba(0,0,0,0.1); max-width: 600px; margin: 20px auto; }
        h1 { color: #5cb85c; text-align: center; }
        p { font-size: 1.1em; line-height: 1.6; }
        strong { color: #337ab7; }
        .details { margin-top: 20px; padding-top: 20px; border-top: 1px solid #eee; }
        .details p { margin: 5px 0; }
    </style>
</head>
<body>
    <div class="container">
        <h1>Pedido Concluído com Sucesso!</h1>
        <p>Obrigado pelo seu pedido. As informações foram recebidas com sucesso.</p>

        <div class="details">
            <h2>Detalhes do Pedido:</h2>
            <p><strong>Nome:</strong> <%= request.getAttribute("nome") %></p>
            <p><strong>Email:</strong> <%= request.getAttribute("email") %></p>
            <p><strong>Telefone:</strong> <%= request.getAttribute("telefone") %></p>
            <p><strong>Forma de Pagamento:</strong> <%= request.getAttribute("pagamento") %></p>
            <p><strong>Endereço:</strong> <%= request.getAttribute("endereco") %></p>
        </div>

        <p style="text-align: center; margin-top: 30px;"><a href="index.html">Voltar para a página inicial</a></p>
    </div>
</body>
</html> 