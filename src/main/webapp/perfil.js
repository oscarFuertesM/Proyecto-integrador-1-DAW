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


/*document.addEventListener('DOMContentLoaded', function() {
    fetch('ListarEquipos', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Usuario no autenticado');
        }
        return response.json();
    })
    .then(data => {
        const equiposContainer = document.getElementById('equipos');
        data.forEach(equipo => {
            const equipoDiv = document.createElement('div');
            equipoDiv.classList.add('equipo');
            const datosEquipo = JSON.parse(equipo.datosEquipo);

            datosEquipo.equipo.forEach(pokemon => {
                const pokemonP = document.createElement('p');
                pokemonP.textContent = `Pokemon: ${pokemon.nombre}`;
                pokemonP.classList.add('pokemon');
                equipoDiv.appendChild(pokemonP);

                pokemon.movimientos.forEach(mov => {
                    const movP = document.createElement('p');
                    movP.textContent = `Movimiento: ${mov}`;
                    movP.classList.add('movimiento');
                    equipoDiv.appendChild(movP);
                });
            });

            equiposContainer.appendChild(equipoDiv);
        });
    })
    .catch(error => {
        console.error('Error al obtener los equipos:', error);
       // window.location.href = 'login.html'; 
    });
});*/

document.addEventListener('DOMContentLoaded', function() {
    fetch('ListarEquipos', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Usuario no autenticado');
        }
        return response.json();
    })
    .then(data => {
        const equiposContainer = document.getElementById('equipos');
        data.forEach(equipo => {
            const equipoDiv = document.createElement('div');
            equipoDiv.classList.add('equipo');
            const datosEquipo = JSON.parse(equipo.datosEquipo);

            datosEquipo.equipo.forEach(pokemon => {
                const pokemonDiv = document.createElement('div');
                pokemonDiv.classList.add('poke');

                const pokemonP = document.createElement('p');
                pokemonP.textContent = pokemon.nombre;
                pokemonP.classList.add('pokemon');
                pokemonDiv.appendChild(pokemonP);

                pokemon.movimientos.forEach(mov => {
                    const movP = document.createElement('p');
                    movP.textContent = mov;
                    movP.classList.add('movimiento');
                    pokemonDiv.appendChild(movP);
                });

                equipoDiv.appendChild(pokemonDiv);
            });

            equiposContainer.appendChild(equipoDiv);
        });
    })
    .catch(error => {
        console.error('Error al obtener los equipos:', error);
        // window.location.href = 'login.html'; 
    });
});















