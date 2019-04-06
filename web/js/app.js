/* iagocolodetti */

// Função 'sortTable' retirada e adaptada de: https://www.w3schools.com/howto/howto_js_sort_table.asp
function sortTable(n) {
    var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
    table = document.getElementById("tabelaContato");
    switching = true;
    dir = "asc"; 
    while (switching) {
        switching = false;
        rows = table.getElementsByTagName("TR");
        for (i = 1; i < (rows.length - 1); i++) {
            shouldSwitch = false;
            x = rows[i].getElementsByTagName("TD")[n];
            y = rows[i + 1].getElementsByTagName("TD")[n];
            if (dir == "asc") {
                if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
                    shouldSwitch = true;
                    break;
                }
            } 
            else if (dir == "desc") {
                if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
                    shouldSwitch = true;
                    break;
                }
            }
        }
        if (shouldSwitch) {
            rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
            switching = true;
            switchcount++;      
        } 
        else {
            if (switchcount == 0 && dir == "asc") {
                dir = "desc";
                switching = true;
            }
        }
    }
}

function confirmaAlterar(id, nome, email, telefone) {
    if (confirm("Deseja realmente alterar os dados desse contato?")) {
        location.href = "alterar?id=" + id + "&nome=" + nome + "&email=" + email + "&telefone=" + telefone;
    }
}

function confirmaRemover(id, nome, email, telefone) {
    if (confirm("Deseja realmente remover esse contato?")) {
        location.href = "remover?id=" + id + "&nome=" + nome + "&email=" + email + "&telefone=" + telefone;
    }
}