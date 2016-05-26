<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<html>
<head>
    <title>Clothing Shop</title>
    <%@include file="WEB-INF/jspf/css.jspf" %>
</head>
<body>
<%@include file="WEB-INF/jspf/top_bar.jspf" %>
<div class="container">
    <div class="row">
        <div class="col-md-3">
            <div class="panel panel-default">
                <div class="panel-body">
                    <ul class="nav nav-pills nav-stacked">
                        <%--@elvariable id="categories" type="java.util.List"--%>
                        <c:forEach items="${categories}" var="cat">
                            <li role="presentation" class="
                    <c:if test="${param.category != null && param.category != 'null' && param.category==cat.id}">
                        <c:out value="active"/>
                        </c:if>">
                                <a href="<c:url value="items?category=${cat.id}"/>"><c:out
                                        value="${cat.title}"></c:out></a>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
        <div class="col-md-7">

            <div class="row">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <form class="form-horizontal" action="login" method="post">
                            <fieldset>

                                <!-- Form Name -->
                                <legend>Авторизация</legend>

                                <!-- Text input-->
                                <div class="form-group">
                                    <label class="col-md-4 control-label" for="login">Логин</label>
                                    <div class="col-md-4">
                                        <input id="login" name="login" type="text" placeholder="введите логин"
                                               class="form-control input-md" required="">

                                    </div>
                                </div>

                                <!-- Text input-->
                                <div class="form-group">
                                    <label class="col-md-4 control-label" for="pass">Пароль</label>
                                    <div class="col-md-4">
                                        <input id="pass" name="pass" type="password" placeholder="введите пароль"
                                               class="form-control input-md" required="">

                                    </div>
                                </div>

                                <!-- Button -->
                                <div class="form-group">
                                    <label class="col-md-4 control-label" for="singlebutton"></label>
                                    <div class="col-md-4">
                                        <button id="singlebutton" type="submit" name="singlebutton"
                                                class="btn btn-success">Войти
                                        </button>
                                    </div>
                                </div>

                                <c:if test="${sessionScope.message != null and sessionScope.message == true}">
                                    <div class="form-group">
                                        <div class="col-md-4 col-md-offset-4">
                                            <label class="col-md-4 control-label" for="messageAlert"></label>
                                            <div id="messageAlert" class="alert alert-danger fade in">
                                                <a href="#" class="close" data-dismiss="alert"
                                                   aria-label="close">&times;</a>
                                                Неверный логин или пароль
                                            </div>
                                        </div>
                                    </div>
                                    <c:set var="message" value="false" scope="session"/>
                                </c:if>
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>
<%@include file="WEB-INF/jspf/scripts.jspf" %>
</body>
</html>
