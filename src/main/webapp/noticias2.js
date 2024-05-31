function noticias() {
    let xhr = new XMLHttpRequest();
    xhr.onload = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
            try {
                let datosNoticia = JSON.parse(xhr.responseText);
                console.log(datosNoticia);
                datosNoticia.forEach(noticia => generarnoticias(noticia));
            } catch (e) {
                console.error("Error al parsear noticias:", e);
            }
        }
    };

    xhr.open("GET", "GestiónNoticia", true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send();
}



var datosNoticia = noticias();

function generarnoticias(noticia) {
    var section = document.getElementById('notisid');
    if (section) {
        var div = document.createElement('div');
        div.className = 'publicacion';

        div.innerHTML = `<h2 class="titulo">${noticia.titulo}</h2>
                         <p class="texto">${noticia.texto}</p>
                         <button class="botonEditar"><a href="insertarnoticias.html?id=${noticia.idNoti}">Editar</a></button>
                         <button class="botonBorrar"><a href="javascript:borrarnoticia(${noticia.idNoti})">Borrar</a></button>`;

        section.insertBefore(div, section.firstChild);
    }
}

//window.addEventListener("DOMContentLoaded", noticias())

function aplicarVisibilidadBotones() {
    fetch('obtenerPermisos_id')
    .then(response => response.json())
    .then(data => {
        const permisos = data.permiso;
        const botonesEditar = document.querySelectorAll(".botonEditar");
        const botonesBorrar = document.querySelectorAll(".botonBorrar");

        botonesEditar.forEach(boton => boton.style.display = permisos >= 7 ? "block" : "none");
        botonesBorrar.forEach(boton => boton.style.display = permisos >= 7 ? "block" : "none");
    })
    .catch(error => {
        console.error("Error al obtener permisos:", error);
        document.querySelectorAll(".botonEditar, .botonBorrar").forEach(boton => boton.style.display = "none");
    });
}

window.addEventListener("DOMContentLoaded", function() {
    aplicarVisibilidadBotones();
});


window.addEventListener("load", aplicarVisibilidadBotones);




function borrarnoticia(idNoti) {
	if (confirm("Se eliminará la noticia de manera permanente, ¿estás seguro?")) {
		fetch('BorrarNoticias?id=' + idNoti)
		window.location.reload();
	}
}

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
    if (confirm("¿Estás seguro de que deseas cerrar sesión?")) {
        localStorage.clear();
        sessionStorage.clear();
        fetch('cerrarSesion')
        .then(response => {
            window.location.href = 'login.html'; 
        })
        .catch(error => console.error('Error al cerrar sesión:', error));
    } else {
        console.log('Cierre de sesión cancelado.');
    }
});














