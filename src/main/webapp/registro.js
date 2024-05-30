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