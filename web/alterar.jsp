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
                <form class="btn-group" action="agenda" method="GET">
                    <button type="submit" class="btn btn-link">Agenda</button>
                </form>
                <form class="btn-group">
                    <h5> | </h5>
                </form>
                <form class="btn-group" action="logout" method="POST">
                    <button type="submit" class="btn btn-link">Sair</button>
                </form>
            </div>
            <br>
            <br>
            <div class="row">
                <div class="form-group col-xs-10 col-sm-8 col-md-6 col-lg-6 col-xs-offset-1 col-sm-offset-2 col-md-offset-3 col-lg-offset-3 text-center">
                    <h4>Alterar Contato</h4>
                </div>
            </div>
            <form action="alterar" method="POST">
                <input type="hidden" id="id" name="id" value="${id}" readonly>  
                <div class="row">
                    <div class="form-group col-xs-10 col-sm-8 col-md-6 col-lg-6 col-xs-offset-1 col-sm-offset-2 col-md-offset-3 col-lg-offset-3 text-center">
                        <label for="nomeatual">Nome:</label>
                        <input type="text" class="form-control text-center" id="nomeatual" name="nomeatual" value="${nomeatual}" readonly>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-xs-10 col-sm-8 col-md-6 col-lg-6 col-xs-offset-1 col-sm-offset-2 col-md-offset-3 col-lg-offset-3 text-center">
                        <label for="emailatual">Email:</label>
                        <input type="emailatual" class="form-control text-center" id="emailatual" name="emailatual" value="${emailatual}" readonly>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-xs-10 col-sm-8 col-md-6 col-lg-6 col-xs-offset-1 col-sm-offset-2 col-md-offset-3 col-lg-offset-3 text-center">
                        <label for="telefoneatual">Telefone:</label>
                        <input type="text" class="form-control text-center" id="telefoneatual" name="telefoneatual" value="${telefoneatual}" readonly>
                    </div>
                </div>
                <hr>
                <div class="row">
                    <div class="form-group col-xs-10 col-sm-8 col-md-6 col-lg-6 col-xs-offset-1 col-sm-offset-2 col-md-offset-3 col-lg-offset-3 text-center">
                        <label for="novonome">Novo Nome:</label>
                        <input type="text" maxlength="50" class="form-control text-center" id="novonome" placeholder="Digite o novo nome" name="novonome"
                               required oninvalid="this.setCustomValidity('Entre com o novo nome do contato.')" oninput="this.setCustomValidity('')"
                               value="${novonome}">
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-xs-10 col-sm-8 col-md-6 col-lg-6 col-xs-offset-1 col-sm-offset-2 col-md-offset-3 col-lg-offset-3 text-center">
                        <label for="novoemail">Novo Email:</label>
                        <input type="email" maxlength="70" class="form-control text-center" id="novoemail" placeholder="Digite o novo email" name="novoemail"
                               required oninvalid="this.setCustomValidity('Entre com o novo email do contato.')" oninput="this.setCustomValidity('')"
                               value="${novoemail}">
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-xs-10 col-sm-8 col-md-6 col-lg-6 col-xs-offset-1 col-sm-offset-2 col-md-offset-3 col-lg-offset-3 text-center">
                        <label for="novotelefone">Novo Telefone:</label>
                        <input type="number" maxlength="13" class="form-control text-center" id="novotelefone" placeholder="Digite o novo telefone" name="novotelefone"
                               required oninvalid="this.setCustomValidity('Entre com o novo telefone do contato.')" oninput="this.setCustomValidity('')"
                               value="${novotelefone}">
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-xs-10 col-sm-8 col-md-6 col-lg-6 col-xs-offset-1 col-sm-offset-2 col-md-offset-3 col-lg-offset-3 text-center">
                        <button type="submit" class="btn btn-primary btn-largura-padrao">Alterar</button>
                    </div>
                </div>
                <c:if test="${erro != null}">
                    <div class="row">
                        <div class="form-group col-xs-10 col-sm-8 col-md-6 col-lg-6 col-xs-offset-1 col-sm-offset-2 col-md-offset-3 col-lg-offset-3 text-center">
                            <span class="help-block" style="color:red">${erro}</span>
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
