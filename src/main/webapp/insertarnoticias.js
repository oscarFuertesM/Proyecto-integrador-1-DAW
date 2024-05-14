function editarnoticia(idNoti){
	if(idNoti){
		console.log("estoy aqui");
	fetch('EditarNoticias?id='+idNoti)
	.then(response => response.json())
	.then(data => devolverDatos(data))
	}else{
		console.log("hola");
	}
}

function getParameterByName(name) {
		    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
		    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
		    results = regex.exec(location.search);
		    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
	}
	
function devolverDatos(datos) {
   document.getElementById("id").value = datos.idNoti;
   document.getElementById("titulo").value = datos.titulo;
   document.getElementById("contenido").value = datos.texto;
   

}
	
window.addEventListener("DOMContentLoaded", function(){
		
		let id = getParameterByName("id");
		editarnoticia(id);
		
	})
	
function ajustarMenuUsuario() {
    fetch('obtenerPermisos_id', { cache: 'no-store' }) 
    .then(response => response.json())
    .then(data => {
        const permisos = data.permiso;
        const idUsuario = data.id;

        const loginLink = document.getElementById('login');
        const adminLink = document.getElementById('admin');
        const logoutLink = document.getElementById('logout');

        if (idUsuario > 0) {
            loginLink.style.display = 'none';
            logoutLink.style.display = 'block';
            adminLink.style.display = permisos >= 7 ? 'block' : 'none';
        } else {
            loginLink.style.display = 'block';
            adminLink.style.display = 'none';
            logoutLink.style.display = 'none';
        }
    })
    .catch(error => {
        console.error('Error al obtener el estado de la sesión:', error);
    });
}

window.addEventListener('DOMContentLoaded', ajustarMenuUsuario);



document.getElementById('logout').addEventListener('click', function() {
    localStorage.clear(); 
    sessionStorage.clear(); 
    fetch('/cerrarSesion')
    .then(response => {
        window.location.href = 'login.html'; 
    })
    .catch(error => console.error('Error al cerrar sesión:', error));
});