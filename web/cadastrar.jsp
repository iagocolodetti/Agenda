<!-- iagocolodetti -->
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
            <div class="btn-group pull-right">
                <form class="btn-group" action="index" method="GET">
                    <button type="submit" class="btn btn-link">Login</button>
                </form>
            </div>
            <br>
            <br>
            <div class="row">
                <div class="form-group col-xs-10 col-sm-8 col-md-6 col-lg-6 col-xs-offset-1 col-sm-offset-2 col-md-offset-3 col-lg-offset-3 text-center">
                    <h4>Cadastrar Usuário</h4>
                </div>
            </div>
            <form action="cadastrar" method="POST">
                <div class="row">
                    <div class="form-group col-xs-10 col-sm-8 col-md-6 col-lg-6 col-xs-offset-1 col-sm-offset-2 col-md-offset-3 col-lg-offset-3 text-center">
                        <label for="nome">Nome de usuário:</label>
                        <input type="text" maxlength="20" class="form-control text-center" id="nome" placeholder="Digite seu nome" name="nome"
                               required oninvalid="this.setCustomValidity('Entre com seu nome de usuário.')" oninput="this.setCustomValidity('')"
                               value="${nome}">
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-xs-10 col-sm-8 col-md-6 col-lg-6 col-xs-offset-1 col-sm-offset-2 col-md-offset-3 col-lg-offset-3 text-center">
                        <label for="senha">Senha:</label>
                        <input type="password" maxlength="32" class="form-control text-center" id="senha" placeholder="Digite uma senha" name="senha"
                               required oninvalid="this.setCustomValidity('Entre com uma senha para seu acesso.')" oninput="this.setCustomValidity('')"
                               value="${senha}">
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-xs-10 col-sm-8 col-md-6 col-lg-6 col-xs-offset-1 col-sm-offset-2 col-md-offset-3 col-lg-offset-3 text-center">
                        <button type="submit" class="btn btn-primary btn-largura-padrao">Cadastrar</button>
                    </div>
                </div>
                <c:if test="${sucesso != null || erro != null}">
                    <div class="row">
                        <div class="form-group col-xs-10 col-sm-8 col-md-6 col-lg-6 col-xs-offset-1 col-sm-offset-2 col-md-offset-3 col-lg-offset-3 text-center">
                            <c:choose>
                                <c:when test="${sucesso != null}">
                                    <span class="help-block" style="color: limegreen">${sucesso}</span>
                                </c:when>
                                <c:when test="${erro != null}">
                                    <span class="help-block" style="color: red">${erro}</span>
                                </c:when>
                            </c:choose>
                        </div>
                    </div>
                </c:if>
            </form>
            <div class="row">
                <div class="form-group col-xs-10 col-sm-8 col-md-6 col-lg-6 col-xs-offset-1 col-sm-offset-2 col-md-offset-3 col-lg-offset-3 text-center">
                    <span class="help-block">@IagoColodetti</span>
                </div>
            </div>
        </div>
    </body>
</html>
