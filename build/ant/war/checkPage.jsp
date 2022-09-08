<%@ page import="teste.domain.SectionImpl" %>
<%@ page import="teste.domain.PageImpl" %>
<%@ page import="teste.domain.Page" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<!DOCTYPE html>

<head>
    <style>

        body {
            background-color: #FAF9F6;
        }

    </style>


</head>


<body>

<% String idPage = request.getParameter("idPage");%>

<div id="myApp" ng-app="myApp" ng-controller="myCtrl" ng-repeat="p in pages">

    <h2 id="titlePage"> {{p.title}} </h2>
    <hr id="HR">
    <div class="panel panel-default">
        <div class="panel-heading" ng-repeat="s in p.sections">
            <h3>{{s.title}}</h3>
            <div class="panel-body" ng-repeat="c in s.components">
                <h4>{{c.text}}</h4>
               <!-- <img src="./img/{{c.img}}"/>-->
                <hr>
            </div>
        </div>
    </div>
</div>


<!-- Section-->

<div id="myApp" ng-app="myApp" ng-controller="myCtrl" ng-repeat="p in pages">
    <div>

        <table style="width: 75%; margin-left: 1%;" class="clearfix table">
            <thead>
            <tr>
                <th>Title</th>
            </tr>
            </thead>

            <tbody ng-app="myApp" ng-controller="myCtrl" ng-repeat="s in sections" class="clearfix">
            <tr>
                <td>{{s.title}}</td>
            </tr>
            </tbody>

        </table>
    </div>
</div>


<script>

    //Components
    const compData = {
        text: null,
        img: null
    }


    //Sections
    const sectionData = {
        title: null
    }

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

    function changeText() {
        compData.text = document.getElementById("text").value;
    }

    function changeImg() {
        compData.img = document.getElementById("img").value;
    }

    function changeTitle() {
        sectionData.title = document.getElementById("title").value;
    }

    function saveSection() {
        send(
            "SectionService",
            "addSection",
            sectionData,
            function (result) {
            },
        );
    }

    function saveComponent() {
        send(
            "ComponentService",
            "addComponent",
            compData,
            function (result) {
            },
        );
    }

    let app = angular.module("myApp", []);
    app.controller("myCtrl", function ($scope) {

        $scope.id = pageData.id;
        $scope.pages = {
            sections: [
                {components: []}
            ]
        };

        $scope.loadPage = function () {
            send(
                "PageService",
                "loadPage",
                {},
                function (result) {
                    $scope.pages = result;
                    $scope.$apply();
                }
            );
        };

        $scope.loadComponents = function () {
            send(
                "ComponentService",
                "returnComp",
                {id: $scope.id,},
                function (result) {
                    $scope.pages.sections.components = result;
                    $scope.$apply();
                }
            );
        };
        $scope.loadPage();
        $scope.loadComponents();

        $scope.saveComponent = function () {
            send(
                "ComponentService",
                "addComponent",
                compData,
                function (result) {
                },
            );
        }


    });


</script>

</body>