<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
	<h2>Calculadora Web</h2>
	<form action="calcular" method="POST">
		<c:if test="${not empty erros}">
			<div style="color: red;" id="divErros">
			<c:forEach var="erro" items="${erros}">
				<span>Campo: ${erro.key} - ${erro.value}</span>
				<br/>
			</c:forEach>
			</div>
		</c:if>
		<label for="num1">Número 1:</label>
		<input type="text" id="num1" name="num1" value="${num1}"> 
		<br/>
		<label for="num2">Número 2:</label>
		<input type="text" id="num2" name="num2" value="${num2}">
		<br/>
		<label for="operacao">Operação:</label>
		<select name="operacao" id="operacao">
			<c:choose>
				<c:when test="${empty operacaoSelecionada}">
					<c:forEach items="${operacoes}" var="operacao">
						<option value="${operacao}">${operacao.label}</option>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<c:forEach items="${operacoes}" var="operacao">
						<c:if test="${operacao eq operacaoSelecionada}">
							<option value="${operacao}" selected >${operacao.label}</option>
						</c:if>
						<c:if test="${not (operacao eq operacaoSelecionada)}">
							<option value="${operacao}">${operacao.label}</option>
						</c:if>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</select>
		<br/>
		<input type="hidden" name="modo" id="modo" value="CALCULAR">
		<input type="submit" id="btnCalcular" value="Calcular">
	</form>
	<c:if test="${not empty resultado}">
		<b>Resultado:</b> <span id="resultado">${resultado}</span>
	</c:if>
</body>
</html>
