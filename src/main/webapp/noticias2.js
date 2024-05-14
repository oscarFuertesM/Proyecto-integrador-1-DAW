function noticias() {
	let resultado;
	let xhr = new XMLHttpRequest();
	xhr.onload = function() {
		if (xhr.readyState === 4) {
			if (xhr.status === 200) {
				try {
					resultado = JSON.parse(xhr.responseText);
					console.log(resultado);
				} catch (e) {
					// TODO: handle exception

				}
			}
		}
	};

	xhr.open("GET", "GestiónNoticia", false);
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.send();

	return resultado;
}

window.addEventListener("DOMContentLoaded", noticias())

var datosNoticia = noticias();

function generarnoticias(noticia) {

	var section = document.getElementById('notisid');
	if (section) {

		var div = document.createElement('div');
		div.className = 'publicacion';

		div.innerHTML = '<h2 class="titulo">' + noticia.titulo + '</h2><p class="texto">' + noticia.texto + '</p><button class="botonEditar"><a href="insertarnoticias.html?id=' + noticia.idNoti + '">Editar</a></button><button class="botonBorrar"><a href="javascript:borrarnoticia(' + noticia.idNoti + ')">Borrar</a></button>';

		section.insertBefore(div, section.firstChild);
		botones();
	} else {
	}
}

if (datosNoticia) {
	datosNoticia.forEach(function(noticia) {
		generarnoticias(noticia);
		
	})
} else {
	console.error("No se pudo obtener la información de las noticias")
}

function borrarnoticia(idNoti) {
	if (confirm("Se eliminará la noticia de manera permanente, ¿estás seguro?")) {
		fetch('BorrarNoticias?id=' + idNoti)
		window.location.reload();
	}
}

/*function devolverDatos(datos) {
	document.getElementById("id").value = datos.idNoti;
	document.getElementById("titulo").value = datos.titulo;
	document.getElementById("contenido").value = datos.texto;


}

function getParameterByName(name) {
	name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
	var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
		results = regex.exec(location.search);
	return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}*/



/*function botones() {

	var permisos = obtenerPermisos();

	var botonEditar = document.getElementById("botonEditar");
	var botonBorrar = document.getElementById("botonBorrar");

	if (permisos > 3) {
		botonEditar.style.display = "block";
		botonBorrar.style.display = "block";

	} else {
		botonEditar.style.display = "none";
		botonBorrar.style.display = "none";

	}
};

function obtenerPermisos() {

	let datosSesion
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4) {
			if (xhr.status === 200) {

				datosSesion = JSON.parse(xhr.responseText);
				console.log("hola");
				console.log(datosSesion);
			}
		};
		xhr.open('GET', 'obtenerPermisos_id', false);
		xhr.setRequestHeader('Content-Type', 'application/json');
		xhr.send();
	return datosSesion;
	}
}

window.addEventListener("DOMContentLoaded", function(){
	//obtenerPermisos();
	botones();
})*/

function botones(permisos) {
    var botonEditar = document.getElementById("botonEditar");
    var botonBorrar = document.getElementById("botonBorrar");

    if (permisos > 3) { // Asegúrate de que el umbral de permisos es correcto
        botonEditar.style.display = "block";
        botonBorrar.style.display = "block";
    } else {
        botonEditar.style.display = "none";
        botonBorrar.style.display = "none";
    }
}

function obtenerPermisos() {
    fetch('obtenerPermisos_id')
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok ' + response.statusText);
        }
        return response.json();
    })
    .then(datosSesion => {
        console.log("Permisos recibidos:", datosSesion);
        botones(datosSesion.permiso); // Llama a botones con los permisos recibidos
    })
    .catch(error => {
        console.error('There was a problem with the fetch operation:', error);
    });
}

window.addEventListener("DOMContentLoaded", function() {
    obtenerPermisos(); // Solo llama a obtenerPermisos
});











