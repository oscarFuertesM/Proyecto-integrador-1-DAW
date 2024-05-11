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

		// Aquí es donde se copia el codigo HTML que queremos insertar
		//div.innerHTML = '<h2 class="titulo">' + noticia.titulo + '</h2><p class="texto">' + noticia.texto + "</p><button id=><a href='insertarnoticias.html?id="+noticia.idNoti+"'>Editar</a></button><button><a href='javascript:borrarnoticia()'>Borrar</a></button>";         

		//div.innerHTML = '<h2 class="titulo">' + noticia.titulo + '</h2><p class="texto">' + noticia.texto + '</p><button><a href="insertarnoticias.html?id='+noticia.idNoti+'">Editar</a></button><button id="btnborrar"><a href="BorrarNoticias?id='+noticia.idNoti+'">Borrar</a></button>';         
		div.innerHTML = '<h2 class="titulo">' + noticia.titulo + '</h2><p class="texto">' + noticia.texto + '</p><button><a href="insertarnoticias.html?id='+noticia.idNoti+'">Editar</a></button><button id="btnborrar"><a href="javascript:borrarnoticia('+noticia.idNoti+')">Borrar</a></button>';         

		// Metemos el Div dentro del Section
		section.insertBefore(div, section.firstChild);
	} else {
	}
}

if(datosNoticia){
	datosNoticia.forEach(function(noticia){
		generarnoticias(noticia);
	})
}else{
	console.error("No se pudo obtener la información de las noticias")
}

function borrarnoticia(idNoti){
	if(confirm("Se eliminará la noticia de manera permanente, ¿estás seguro?")){
	fetch('BorrarNoticias?id='+idNoti)
	window.location.reload();
	}
}

function devolverDatos(datos) {
   document.getElementById("id").value = datos.idNoti;
   document.getElementById("titulo").value = datos.titulo;
   document.getElementById("contenido").value = datos.texto;
   

}

function getParameterByName(name) {
		    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
		    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
		    results = regex.exec(location.search);
		    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
	}


