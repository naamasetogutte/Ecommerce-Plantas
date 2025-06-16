package com.verdeurbano;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

public class Main {
    public static void main(String[] args) {
        // Configurar porta (opcional, padrão é 4567)
        port(4567);
        
        // Configurar diretório de arquivos estáticos
        staticFiles.location("/public");
        
        // Configuração do Gson para JSON
        Gson gson = new Gson();
        
        // Log para debug
        System.out.println("Iniciando servidor Spark Java na porta 4567...");
        System.out.println("Diretório de arquivos estáticos: /public");

        // Rota principal
        get("/", (req, res) -> {
            res.redirect("/index.html");
            return null;
        });
        
        // Rota para obter uma planta/produto específico (para o carrinho)
        get("/plantas/:id", (req, res) -> {
            res.type("application/json");
            int id = Integer.parseInt(req.params(":id"));
            Map<String, Object> produto = new HashMap<>();

            // Dados de exemplo baseados no seu catalogo.html
            switch (id) {
                case 1:
                    produto = createProduto(1, "Capim-dourado", "Resistente à seca e ideal para jardins sustentáveis.", 25.00, "/img/CapimDourado.jpg");
                    break;
                case 2:
                    produto = createProduto(2, "Ipê-amarelo", "Árvore símbolo do Brasil, com floração vibrante.", 85.00, "/img/flores-de-ipe.webp");
                    break;
                case 3:
                    produto = createProduto(3, "Palmeira-jerivá", "Atrai fauna e resiste bem a solos pobres.", 60.00, "/img/ipe-roxo-de-bola-tabebuia-impetiginosa-muda-jardim-exotico-bilwde-1024x707.webp");
                    break;
                case 4:
                    produto = createProduto(4, "Consultoria e Projeto 3D", "Desenvolvemos projetos personalizados para seu telhado verde, com visualizações 3D realistas e planejamento detalhado. Inclui análise de viabilidade e sugestão de espécies.", 250.00, "/img/TELHADO-VERDE-3D.jpg");
                    break;
                case 5:
                    produto = createProduto(5, "Instalação Completa por m²", "Nossa equipe especializada realiza a instalação completa do seu telhado verde, garantindo durabilidade e eficiência. Inclui camadas de drenagem, substrato e vegetação selecionada.", 180.00, "/img/TELHADO-VERDE-EXTERNO.jpg");
                    break;
                case 6:
                    produto = createProduto(6, "Guia Essencial e Workshop Online", "Aprenda tudo sobre a funcionalidade, benefícios e manutenção de telhados verdes através do nosso guia detalhado e acesso a um workshop exclusivo. Ideal para quem busca conhecimento aprofundado.", 90.00, "/img/TELHADO-VERDE.gif");
                    break;
                default:
                    res.status(404);
                    return "Produto não encontrado";
            }
            return gson.toJson(produto);
        });

        // Rota POST para /cadastro (formulário de contato)
        post("/cadastro", (req, res) -> {
            res.type("application/json");
            Map<String, String> response = new HashMap<>();
            
            try {
                // Aqui você pode implementar a lógica para processar o formulário
                // Por enquanto, apenas retornaremos uma resposta de sucesso
                response.put("status", "success");
                response.put("message", "Mensagem enviada com sucesso!");
                return gson.toJson(response);
            } catch (Exception e) {
                res.status(500);
                response.put("status", "error");
                response.put("message", "Erro ao processar a mensagem: " + e.getMessage());
                return gson.toJson(response);
            }
        });

        // Nova rota POST para /finalizar-pedido
        post("/finalizar-pedido", (req, res) -> {
            res.type("application/json");
            Map<String, String> response = new HashMap<>();
            try {
                String requestBody = req.body();
                System.out.println("Pedido recebido: " + requestBody); // Logar o corpo da requisição
                
                // Implemente a lógica para processar e salvar o pedido aqui
                // Por exemplo: salvar no banco de dados, enviar email, etc.

                response.put("status", "success");
                response.put("message", "Pedido finalizado com sucesso!");
                res.status(200);
                return gson.toJson(response);
            } catch (Exception e) {
                res.status(500);
                response.put("status", "error");
                response.put("message", "Erro ao finalizar o pedido: " + e.getMessage());
                System.err.println("Erro ao processar pedido: " + e.getMessage()); // Logar o erro
                return gson.toJson(response);
            }
        });

        // Log de inicialização
        System.out.println("Servidor Spark Java iniciado com sucesso!");
        System.out.println("Acesse: http://localhost:4567");
    }
    
    // Método auxiliar para criar dados de exemplo de produtos
    private static Map<String, Object> createProduto(int id, String nome, String descricao, double preco, String imagem) {
        Map<String, Object> produto = new HashMap<>();
        produto.put("id", id);
        produto.put("name", nome); 
        produto.put("description", descricao); 
        produto.put("price", preco);
        produto.put("imageUrl", imagem); 
        return produto;
    }
} 