<%@ page import="teste.domain.PageImpl" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js%22%3E"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<!DOCTYPE html>

<head>
<style>

    body{
        align-content: center;
        background-color: #FAF9F6;
    }

    input {
        color: black;
    }

    span{
        color: black;
    }

    .glyphicon glyphicon-ok{
        color: green;
    }
</style>



</head>


<body>

<div id="myApp" class="container" ng-app="myApp" ng-controller="myCtrl">
    <table class="table clearfix">
        <thead>
        <tr>
            <th>title</th>
            <th>Roles</th>
            <th>ID</th>
            <th>Add Page</th>
            <th>Roles</th>
            <th>Pages</th>
        </tr>
        </thead>

        <tbody ng-app="myApp" ng-controller="myCtrl" ng-repeat="p in pages" class="clearfix">
        <tr>
            <td>{{p.title}}</td>
            <td>{{p.roles}}</td>
            <td>{{p.id}}</td>
            <td> - </td>
            <a class="button" href="<%=request.getContextPath()%>/checkPage.do?id={{p.id}}">
                <span style="color:seagreen" class="glyphicon-ok"></span>
            </a></td>
        </tr>
        </tbody>
    </table>
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

        $scope.pages = [];

        $scope.listPages = function (){
            send (
                "PageService",
                "loadAll",
                {},
                function (result){
                    $scope.pages = result;
                    $scope.$apply();
                }
            );
        };
        $scope.listPages();
    });


</script>

</body>