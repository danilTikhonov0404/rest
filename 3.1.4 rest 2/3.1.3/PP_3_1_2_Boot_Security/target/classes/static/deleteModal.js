$('#delete').on('show.bs.modal', ev => {
    let button = $(ev.relatedTarget);
    let id = button.data('id');
    showDeleteModal(id);
})

async function showDeleteModal(id) {
    let user = await getUser(id);
    let form = document.forms["formDeleteUser"];
    form.id.value = user.id;
    form.name.value = user.name;
    form.surname.value = user.surname;
    form.age.value = user.age;
    form.password.value = user.password;

    $('#rolesDeleteUser').empty();

    await fetch("http://localhost:8080/api/roles")
        .then(res => res.json())
        .then(roles => {
            roles.forEach(role => {
                let selectedRole = false;
                for (let i = 0; i < user.roles.length; i++) {
                    if (user.roles[i].roleName === role.roleName) {
                        selectedRole = true;
                        break;
                    }
                }
                let el = document.createElement("option");
                el.text = role.roleName.substring(5);
                el.value = role.id;
                if (selectedRole) el.selected = true;
                $('#rolesDeleteUser')[0].appendChild(el);
            })
        });
}
async function getUser(id) {
    let url = "http://localhost:8080/api/users/" + id;
    let response = await fetch(url);
    return await response.json();
}