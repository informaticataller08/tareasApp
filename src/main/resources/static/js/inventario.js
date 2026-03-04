/**
 * TareasApp - Script de Gestión de Inventario
 */

document.addEventListener('DOMContentLoaded', function() {
    console.log("Inventario JS cargado y listo");

    // === LÓGICA DEL BUSCADOR EN TIEMPO REAL ===
    const buscador = document.getElementById('tablaBuscador');

    if (buscador) {
        buscador.addEventListener('input', function() {
            const valor = this.value.toLowerCase().trim();
            // Buscamos todas las filas dentro del tbody con ID cuerpoTabla
            const filas = document.querySelectorAll('#cuerpoTabla tr:not(#sinResultados)');
            let hayCoincidencias = false;

            filas.forEach(fila => {
                // Comparamos el texto de la fila con lo escrito
                const textoFila = fila.innerText.toLowerCase();

                if (textoFila.includes(valor)) {
                    fila.style.display = ""; // Mostrar
                    hayCoincidencias = true;
                } else {
                    fila.style.display = "none"; // Ocultar
                }
            });

            // Mostrar/Ocultar mensaje de "No se encontraron resultados"
            const mensajeSinResultados = document.getElementById('sinResultados');
            if (mensajeSinResultados) {
                mensajeSinResultados.style.display = hayCoincidencias ? "none" : "";
            }
        });

        // Evitar que el formulario se envíe (recargue) al presionar Enter
        buscador.addEventListener('keydown', function(e) {
            if (e.key === 'Enter') {
                e.preventDefault();
            }
        });
    }
});

// === LÓGICA DE CREACIÓN Y EDICIÓN ===

/**
 * Prepara el modal para un nuevo registro (limpia campos e ID)
 */
function prepararCrear() {
    const modalTitle = document.getElementById('modalTitle');
    const editId = document.getElementById('edit_id');
    const form = document.querySelector('#modalEquipo form');

    if (modalTitle) modalTitle.innerText = "Nueva Ficha de Equipo";
    if (editId) editId.value = ""; // ID vacío para nuevo registro
    if (form) form.reset();
}

/**
 * Busca datos del equipo por ID y rellena el modal
 */
function editarEquipo(id) {
    console.log("Editando equipo ID: " + id);

    fetch('/inventario/editar/' + id)
        .then(response => {
            if (!response.ok) throw new Error('Error al obtener datos');
            return response.json();
        })
        .then(data => {
            // Rellenar campos del modal
            document.getElementById('modalTitle').innerText = "Editar Equipo #" + id;
            document.getElementById('edit_id').value = data.id;
            document.getElementById('edit_nne').value = data.nne || "";
            document.getElementById('edit_red').value = data.red || "Internet";
            document.getElementById('edit_tipo').value = data.tipo || "PC";
            document.getElementById('edit_nombre').value = data.nombreEquipo || "";
            document.getElementById('edit_cuenta').value = data.cuentaUsuario || "";
            document.getElementById('edit_desc').value = data.descripcion || "";
            document.getElementById('edit_oficina').value = data.oficina || "";
            document.getElementById('edit_destino').value = data.destino || "";

            // Mostrar modal
            const modalElement = document.getElementById('modalEquipo');
            const myModal = new bootstrap.Modal(modalElement);
            myModal.show();
        })
        .catch(error => {
            console.error('Error:', error);
            alert('No se pudieron cargar los datos del equipo');
        });
}