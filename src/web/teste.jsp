<%@ page import="teste.Teste" %>
<%@page language="java" contentType="UTF-8" %>
<html>

        <head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>


    <script>


	function send(serviceName, method, divMensagens, callbackOk, callbackErro){

        let data = {
            argumento: "123"
        }
        $.ajax({
            type: "POST",
            url: "<%=request.getContextPath()%>/soa",
            contentType: "application/json",
            dataType: 'json',
            data:  JSON.stringify({
                    servico: "paginas.ServicoDeTeste",
                    op: "hello",
                    args: [],
                    data: data,
                    id : 2 ,
                    trtt : { id : 2}
            }),
            success: function(result){
                console.log(JSON.stringify(result));
                //callbackOk(result.data);
            },
            statusCode:{
                401: function(){
                    console.log(401);
                }
            },
            error: function(resposta)
            {

            }


        });
	}
</script>
        </head>
<%

	Teste t = new Teste();


%>

<html>
<body>
	<%=t.hello()%>

	<button onClick="send()">envia</button>
</body>
</html>