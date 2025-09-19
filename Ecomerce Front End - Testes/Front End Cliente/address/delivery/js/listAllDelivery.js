document.addEventListener('DOMContentLoaded', function () {
    const userList = document.getElementById('user-list');

    const params = new URLSearchParams(window.location.search);
    const clienteId = params.get("id");

    async function carregarEntregas() {
        try {
            const response = await fetch(`http://localhost:8080/customers/${clienteId}`, {
                method: 'GET'
            });

            if (!response.ok) {
                throw new Error('Error loading deliveries: ' + response.statusText);
            }

            const data = await response.json();

            userList.innerHTML = '';

            const cardFixo = document.createElement('div');
            cardFixo.classList.add('card');
            cardFixo.innerHTML = `
                <h2>Add Delivery</h2>
                <div class="actions">
                    <a class="link" href="create-delivery.html?id=${clienteId}">Add</a>
                </div>
            `;
            userList.appendChild(cardFixo);

            if (Array.isArray(data.deliveres)) {
                data.deliveres.forEach(entrega => {
                    const div = document.createElement('div');
                    div.classList.add('card');
                    div.innerHTML = `
                        <h3>${entrega.receiver}</h3>
                        <p>${entrega.street}</p>
                        <p>${entrega.typeResidence} - ${entrega.number} ${entrega.observation ?? ''}</p>
                        <p>${entrega.city}, ${entrega.state} ${entrega.zipCode}</p>
                        <p>${entrega.country}</p> 
                        <div class="actions">
                            <a onclick="excluirEntrega('${entrega.id}', '${clienteId}')" href="#">Delete</a>
                            <p>|</p>
                            <a href="update-delivery.html?id=${clienteId}&entregaId=${entrega.id}">Edit</a>
                        </div>
                    `;
                    userList.appendChild(div);
                });
            } else {
                console.error('Unexpected data format:', data);
            }

        } catch (error) {
            console.error("Error loading deliveries:", error);
        }
    }

    carregarEntregas();
});

async function excluirEntrega(idEntrega, clienteId) {
    if (confirm("Are you sure you want to delete this delivery?")) {
        try {
            const response = await fetch(`http://localhost:8080/customers/${clienteId}/deliveries/${idEntrega}`, {
                method: 'DELETE'
            });

            if (!response.ok) {
                alert("This delivery address is being used in an order.");
                throw new Error('Error deleting delivery: ' + response.statusText);
            }

            alert("Delivery deleted successfully!");
            location.reload();
        } catch (error) {
            console.error("Error deleting delivery:", error);
        }
    }
}
