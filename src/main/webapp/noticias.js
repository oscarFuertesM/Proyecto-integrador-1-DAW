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
		div.innerHTML = "<h2>" + noticia.titulo + "</h2><p>" + noticia.texto + "</p>";

		// Metemos el Div dentro del Section
		section.insertBefore(div, section.firstChild);
	} else {
		console.error("FAIL")
	}
}

if(datosNoticia){
	datosNoticia.forEach(function(noticia){
		generarnoticias(noticia);
	})
}else{
	console.error("No se pudo obtener la información de las noticias")
}

window.addEventListener("DOMContentLoaded", generarnoticias());



/*function noticias() {
    let resultado;
    let xhr = new XMLHttpRequest();
    xhr.onload = function () {
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

function editarNoticia(idNoti) {
	
      // Redireccionar a insertarnoticias.html con el ID de la noticia como parámetro en la URL
    window.location = "insertarnoticias.html?id=" + idNoti;
    
}

//window.addEventListener("DOMContentLoaded", function () {
    var datosNoticia = noticias();

    function generarnoticias(noticia) {
        var section = document.getElementById('notisid');
        if (section) {
            var div = document.createElement('div');
            div.className = 'publicacion';
            
            // Asignar el atributo id de la noticia como id del div
        	div.id = "noticia_" + noticia.idNoti;

            // Crear el botón de edición y asignarle un evento onclick
            var btnEditar = document.createElement('button');
            btnEditar.className = "editar";
            btnEditar.textContent = 'Editar';
           //btnEditar.addEventListener('click', function () {
             //   console.log(puto);
               // editarNoticia(noticia.idNoti); // Llamar a la función de edición con el ID de la noticia
            //});

            // Insertar el botón y la información de la noticia en el div
            div.appendChild(btnEditar);
            div.innerHTML += "<h2>" + noticia.titulo + "</h2><p>" + noticia.texto + "</p>";

            // Insertar el div dentro de la sección
            section.insertBefore(div, section.firstChild);
        } else {
            //console.error("FAIL")
        }
    }

    if (datosNoticia) {
        datosNoticia.forEach(function (noticia) {
            generarnoticias(noticia);
        })
    } else {
        console.error("No se pudo obtener la información de las noticias")
    }
//});
	//document.getElementById('editar').addEventListener
	var botones = document.querySelectorAll('.editar');
		
		botones.forEach(function(boton, resultado){
			boton.addEventListener('click',function(){
				var claseBoton = this.className;
				console.log("ffffff");
                editarNoticia(resultado.idNoti);
                window.location = "insertarnoticias.html?id=" + idNoti;
    			console.log("hola");
			});	
		});
	//.addEventListener('click', function () {
      //          console.log("puto");
        //        editarNoticia(resultado.idNoti); // Llamar a la función de edición con el ID de la noticia
          //  });
















