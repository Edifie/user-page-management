<%@ page import="teste.domain.SectionImpl" %>
<%@ page import="teste.domain.PageImpl" %>
<%@ page import="teste.domain.Page" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<!DOCTYPE html>

<head>
    <style>

        body{
            background-color: #FAF9F6;
        }

    </style>



</head>


<body>

<% String id = request.getParameter("id");%>

<div id="myApp" ng-app="myApp" ng-controller="myCtrl" ng-repeat="p in pages">

    <h2 id="titlePage"> {{p.title}} </h2>
    <hr id="HR">
    <div class="panel panel-default">
        <div class="panel-heading" ng-repeat="s in p.sections">
            <h3>{{s.title}}</h3>
            <div class="panel-body" ng-repeat="c in s.components">
                <h4>{{c.text}}</h4>
                <img src="./img/{{c.img}}"/>
                <hr>
            </div>
        </div>
    </div>
</div>


<script>
    function send(serviceName, method, data, callbackOk) {
        $.ajax({
            type: "POST",
            url: "<%=request.getContextPath()%>/soa",
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify({
                servico: serviceName,
                op: method,
                data: data,
            }),
            success: function (result) {
                console.log(JSON.stringify(result));
                callbackOk(result.data);
            },
            statusCode: {
                401: function () {
                    console.log(401);
                }
            },
            error: function (resposta) {

            }
        });
    }


    let app = angular.module("myApp", []);
    app.controller("myCtrl", function ($scope){

        $scope.id = <%=id%>;
        $scope.pages = {
            sections: [
                { components: []}
            ]
        };

        $scope.loadPage = function (){
            send (
                "PageService",
                "loadPage",
                {},
                function (result){
                    $scope.pages = result;
                    $scope.$apply();
                }
            );
        };


        $scope.loadComponents = function (){
            send (
                "ComponentService",
                "returnComp",
                { id: $scope.id,},
                function (result){
                    $scope.pages.sections.components = result;
                    $scope.$apply();
                }
            );
        };
        $scope.listPages();
        $scope.loadComponents();
    });


</script>

</body>