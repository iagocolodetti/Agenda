<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Projeto Agenda</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link href="css/estilos.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="container-fluid">
            <div class="row">
                <div class="form-group col-xs-10 col-sm-4 col-md-4 col-lg-2 col-xs-offset-1 col-sm-offset-4 col-md-offset-4 col-lg-offset-5 text-center">
                    <h3>Projeto Agenda</h3>
                    <h3>Bem-vindo...</h3>
                </div>
            </div>
            <br>
            <br>
            <form action="login" method="POST">
                <div class="row">
                    <div class="form-group col-xs-10 col-sm-6 col-md-4 col-lg-4 col-xs-offset-1 col-sm-offset-3 col-md-offset-4 col-lg-offset-4 text-center">
                        <label for="nome">Nome de usuário:</label>
                        <input type="text" maxlength="20" class="form-control text-center" id="nome" placeholder="Digite seu nome" name="nome"
                               required oninvalid="this.setCustomValidity('Entre com seu nome de usuário.')" oninput="this.setCustomValidity('')"
                               value="${nome}">
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-xs-10 col-sm-6 col-md-4 col-lg-4 col-xs-offset-1 col-sm-offset-3 col-md-offset-4 col-lg-offset-4 text-center">
                        <label for="senha">Senha:</label>
                        <input type="password" maxlength="32" class="form-control text-center" id="senha" placeholder="Digite sua senha" name="senha"
                               required oninvalid="this.setCustomValidity('Entre com sua senha.')" oninput="this.setCustomValidity('')"
                               value="${senha}">
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-xs-10 col-sm-4 col-md-4 col-lg-2 col-xs-offset-1 col-sm-offset-4 col-md-offset-4 col-lg-offset-5 text-center">
                        <button type="submit" class="btn btn-success btn-largura-padrao">Login</button>
                    </div>
                </div>
                <c:if test="${erro != null}">
                    <div class="row">
                        <div class="form-group col-xs-10 col-sm-4 col-md-4 col-lg-2 col-xs-offset-1 col-sm-offset-4 col-md-offset-4 col-lg-offset-5 text-center">
                            <span class="help-block" style="color: red">${erro}</span>
                        </div>
                    </div>
                </c:if>
            </form>
            <form action="cadastrar" method="GET">
                <div class="row">
                    <div class="form-group col-xs-10 col-sm-4 col-md-4 col-lg-2 col-xs-offset-1 col-sm-offset-4 col-md-offset-4 col-lg-offset-5 text-center">
                        <button type="submit" class="btn btn-primary btn-largura-padrao">Cadastrar</button>
                    </div>
                </div>
            </form>
            <div class="row">
                <div class="form-group col-xs-10 col-sm-4 col-md-4 col-lg-2 col-xs-offset-1 col-sm-offset-4 col-md-offset-4 col-lg-offset-5 text-center">
                    <span class="help-block">@IagoColodetti</span>
                </div>
            </div>
        </div>
    </body>
</html>
