document.querySelector("#loginBtn").addEventListener("click", async () => {
    var id = document.querySelector("#id").value;
    var pw = document.querySelector("#pw").value;

    alert(id);

    var data = {id, pw};
    data = JSON.stringify(data);

    let response = await fetch('login', {
        method: 'POST',
        headers: {
            'Content-type': 'application/json'
        },
        body: data
    });

    let responseData = await response.json();

    console.log(responseData);

    const loginSpan = document.querySelector("#loginSpan");
    loginSpan.innerHTML = `${responseData.name}`;



})