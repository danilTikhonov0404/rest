

$(async function () {
	await allUsers();
});
const table = $('#allUsersTable');
async function allUsers() {
        table.empty()
		fetch("http://localhost:8080/api/users")
			.then(res => res.json())
			.then(data => {
				data.forEach(user => {
					let tableWithUser = `$(
            <tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.surname}</td>
                <td>${user.age}</td>
                <td>${user.roles.map(role => role.roleName.substring(5).concat(" ")).toString().replaceAll(",", "")}</td>
                <td>
                    <button type="button" class="btn btn-info" data-toggle="modal" id="buttonEdit"
                    data-action="edit" data-id="${user.id}" data-target="#edit">Edit</button>
                </td>
                <td>
                    <button type="button" class="btn btn-danger" data-toggle="modal" id="buttonDelete"
                    data-action="delete" data-id="${user.id}" data-target="#delete">Delete</button>
                </td>
            </tr>)`;
			table.append(tableWithUser);
		})
	})
}





