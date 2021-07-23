
fetch('/ldvleh/api/book')
    .then(res => res.json())
    .then(res => {
        var invoiceListNode = document.getElementById('book-list');
        invoiceListNode.innerHTML = "";

        var table = document.createElement("table");
        table.setAttribute("border","1");
        invoiceListNode.appendChild(table);

        res.forEach(book => {

            var tr = document.createElement("tr");
            table.appendChild(tr);

            var td = document.createElement("td");
            var text = document.createTextNode(`${book.title}`);
            td.appendChild(text);
            tr.appendChild(td);

            td = document.createElement("td");
            var button = document.createElement("button");
            button.setAttribute("type","button");
            button.onclick = function() {
                showDetail(`${book.title}`);
            };
            text = document.createTextNode("Details");
            button.appendChild(text);
            td.appendChild(button);
            tr.appendChild(td);


        });

    });


function showDetail(bookTitle){

    str = "/ldvleh/api/book/"+bookTitle
    fetch(str)
    .then(res => res.json())
    .then(res => {
       var bookDetailNode = document.getElementById('book-detail');
        bookDetailNode.innerHTML = "";

        var p = document.createElement("p");
        var text = document.createTextNode(`Title: ${res.title}`);
        p.appendChild(text);
        bookDetailNode.appendChild(p);
    });
}
