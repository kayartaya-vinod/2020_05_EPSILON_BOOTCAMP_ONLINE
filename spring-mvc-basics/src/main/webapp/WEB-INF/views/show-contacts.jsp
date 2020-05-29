<%@ include file="./header.jspf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h3>List of all contacts</h3>
<hr>
<p>Powered by Spring MVC, developed by Vinod</p>

<table class="table table-striped table-bordered table-hover">
	<thead class="thead-dark">
		<tr>
			<th>Name</th>
			<th>Email address</th>
			<th>Phone number</th>
			<th>City</th>
			<th>Action</th>
		</tr>
	</thead>

	<tbody>
		<c:forEach items="${contacts}" var="c1">
			<tr>
				<td>${ c1.gender=='Male' ? 'Mr.': 'Ms.'}${ c1.name }</td>
				<td>${ c1.email }</td>
				<td>${ c1.phone }</td>
				<td>${ c1.city }</td>
				<td>
					<a href="./delete-contact?id=${ c1.id }" class="btn btn-danger">Delete</a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<%@ include file="./footer.jspf"%>