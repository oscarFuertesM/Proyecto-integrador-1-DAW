const debilidadesPokemon = {
	"Normal": ["Lucha"],
	"Fuego": ["Agua", "Tierra", "Roca"],
	"Agua": ["Planta", "Eléctrico"],
	"Planta": ["Fuego", "Hielo", "Veneno", "Volador", "Bicho"],
	"Eléctrico": ["Tierra"],
	"Hielo": ["Fuego", "Lucha", "Roca"],
	"Lucha": ["Volador", "Psíquico"],
	"Tierra": ["Agua", "Planta", "Hielo"],
	"Volador": ["Eléctrico", "Hielo", "Roca"],
	"Veneno": ["Tierra", "Psíquico"],
	"Psíquico": ["Bicho", "Fantasma", "Siniestro"],
	"Bicho": ["Fuego", "Volador", "Roca"],
	"Roca": ["Agua", "Planta", "Lucha", "Tierra"],
	"Fantasma": ["Fantasma", "Siniestro"],
	"Dragón": ["Hielo", "Dragón"],
	"Siniestro": ["Lucha", "Bicho"],
};

const efectividadMovimientos = {
	"Normal": [],
	"Fuego": ["Bicho", "Planta", "Hielo"],
	"Agua": ["Tierra", "Roca", "Fuego"],
	"Eléctrico": ["Agua", "Volador"],
	"Hierba": ["Tierra", "Roca", "Agua"],
	"Hielo": ["Planta", "Tierra", "Volador", "Dragón"],
	"Lucha": ["Normal", "Roca", "Hielo", "Siniestro"],
	"Veneno": ["Hierba",],
	"Tierra": ["Fuego", "Eléctrico", "Veneno", "Roca",],
	"Volador": ["Planta", "Lucha", "Bicho"],
	"Psíquico": ["Lucha", "Veneno"],
	"Bicho": ["Planta", "Psíquico", "Siniestro"],
	"Roca": ["Fuego", "Hielo", "Volador", "Bicho"],
	"Fantasma": ["Fantasma", "Psíquico"],
	"Dragón": ["Dragón"],
	"Siniestro": ["Fantasma", "Psíquico"],
};


var pokemonData;
var movimientosData;

function obtenerPokemon() {
	let xhr = new XMLHttpRequest();
	xhr.onload = function() {
		if (xhr.readyState === 4 && xhr.status === 200) {
			pokemonData = JSON.parse(xhr.responseText);
			const pokemonSelects = document.querySelectorAll(".pokemon");
			pokemonSelects.forEach(select => {
				pokemonData.forEach(pokemon => {
					const option = document.createElement("option");
					option.value = pokemon.nombre;
					option.text = pokemon.nombre;
					select.appendChild(option);
				});

				select.addEventListener("change", function() {
					updateMoves(this);
				});
			});
			obtenerMovimientos();
		}
	};
	xhr.open("GET", "listar", true);
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.send();
}

function obtenerMovimientos() {
	let xhr = new XMLHttpRequest();
	xhr.onload = function() {
		if (xhr.readyState === 4 && xhr.status === 200) {
			movimientosData = JSON.parse(xhr.responseText);
		}
	};
	xhr.open("GET", "GestionMovimientos", true);
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.send();
}

function updateMoves(select) {
	const selectedPokemon = select.value;
	const pokemon = pokemonData.find(pokemon => pokemon.nombre === selectedPokemon);
	const equipoDiv = select.closest('.poke');

	if (!pokemon) {
		const movesSelects = equipoDiv.querySelectorAll(".movs");
		movesSelects.forEach(movesSelect => {
			movesSelect.innerHTML = "";
			const defaultOption = document.createElement("option");
			defaultOption.value = "";
			defaultOption.text = "";
			movesSelect.appendChild(defaultOption);
		});
		return;
	}

	const movesOfType = movimientosData.filter(move => move.tipo === pokemon.tipo1 || move.tipo === pokemon.tipo2);

	const movesSelects = equipoDiv.querySelectorAll(".movs");
	movesSelects.forEach(movesSelect => {
		movesSelect.innerHTML = "";
		const defaultOption = document.createElement("option");
		defaultOption.value = "";
		defaultOption.text = "";
		movesSelect.appendChild(defaultOption);
		movesOfType.forEach(move => {
			const moveOption = document.createElement("option");
			moveOption.value = move.nombre;
			moveOption.text = move.nombre;
			movesSelect.appendChild(moveOption);
		});
	});
}


function cargarDatos() {
	obtenerPokemon();
}


function actualizarContadoresDeTipo(tipo, cambio) {
    const contadores = document.querySelectorAll('.tipos .contador');
    contadores.forEach(contador => {
        const tipoElemento = contador.previousElementSibling.alt.replace('Tipo ', '');
        if (tipo === tipoElemento) {
            let valorActual = parseInt(contador.textContent);
            contador.textContent = valorActual + cambio;
        }
    });
}



function actualizarDebilidades(pokemon, cambio) {
    if (!pokemon) return;
    const tiposDebilidades = debilidadesPokemon[pokemon.tipo1] || [];
    tiposDebilidades.forEach(tipo => actualizarContadoresDeTipo(tipo, cambio));
}

function actualizarFortalezas(movimiento, cambio) {
    if (!movimiento) return;
    const tiposFuertes = efectividadMovimientos[movimiento.tipo] || [];
    tiposFuertes.forEach(tipo => actualizarContadoresDeTipo(tipo, cambio));
}

function manejarCambioPokemon(event) {
    const select = event.target;
    const pokemonAnterior = select.dataset.previous || "";
    const pokemonNuevo = select.value;

   
    if (pokemonAnterior) {
        const pokemon = pokemonData.find(p => p.nombre === pokemonAnterior);
        actualizarDebilidades(pokemon, 1); 
    }

  
    const pokemonSeleccionado = pokemonData.find(p => p.nombre === pokemonNuevo);
    actualizarDebilidades(pokemonSeleccionado, -1);
    select.dataset.previous = pokemonNuevo; 
}

function manejarCambioMovimiento(event) {
    const select = event.target;
    const movimientoAnterior = select.dataset.previous || "";
    const movimientoNuevo = select.value;

    
    if (movimientoAnterior) {
        const movimiento = movimientosData.find(m => m.nombre === movimientoAnterior);
        actualizarFortalezas(movimiento, -1);
    }

    
    const movimientoSeleccionado = movimientosData.find(m => m.nombre === movimientoNuevo);
    actualizarFortalezas(movimientoSeleccionado, 1);
    select.dataset.previous = movimientoNuevo; 
}

document.addEventListener('DOMContentLoaded', () => {
    cargarDatos();
    document.querySelectorAll('.pokemon').forEach(select => {
        select.addEventListener('change', manejarCambioPokemon);
    });
    document.querySelectorAll('.movs').forEach(select => {
        select.addEventListener('change', manejarCambioMovimiento);
    });
});


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

function botonGuardar() {
    fetch('obtenerPermisos_id', { cache: 'no-store' }) 
    .then(response => response.json())
    .then(data => {
        const idUsuario = data.id;
        const botonGuardar = document.getElementById('guardarEquipo');

        if (idUsuario > 0 && botonGuardar) {
            botonGuardar.style.display = 'block';
        } else if (botonGuardar) {
            botonGuardar.style.display = 'none';
        }
    })
    .catch(error => {
        console.error('Error al verificar la sesión:', error);
    });
}

window.addEventListener('DOMContentLoaded', botonGuardar);


document.getElementById('guardarEquipo').addEventListener('click', function() {
    const equipoPokemon = document.querySelectorAll('.poke');
    const equipo = Array.from(equipoPokemon).map(pokeDiv => {
        const nombrePokemonElement = pokeDiv.querySelector('.pokemon');
        const movimientosElements = pokeDiv.querySelectorAll('.movs');

        const nombrePokemon = nombrePokemonElement ? nombrePokemonElement.options[nombrePokemonElement.selectedIndex].value : '';
        const movimientos = Array.from(movimientosElements).map(mov => mov.options[mov.selectedIndex].value);

        return {
            nombre: nombrePokemon,
            movimientos: movimientos
        };
    });

    const datosEquipo = JSON.stringify({ equipo });
    console.log(datosEquipo); // Verificar el objeto JSON antes de enviarlo

    fetch('GestionEquipos', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ datosEquipo }) // Asegúrate de que el formato JSON coincide con el esperado en el servidor
    })
    .then(response => response.json())
    .then(data => {
        alert('Equipo guardado con éxito!');
    })
    .catch(error => {
        console.error('Error al guardar el equipo:', error);
    });
});










