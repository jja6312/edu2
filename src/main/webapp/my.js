document.querySelector("#loginBtn").addEventListener("click", async () => {
    var id = document.querySelector("#id").value;
    var pw = document.querySelector("#pw").value;

    var data = {id, pw, "sign":"login"};
    data = JSON.stringify(data);

    let response = await fetch('login', {
        method: 'POST',
        headers: {
            'Content-type': 'application/json'
        },
        body: data
    });

    let responseData = await response.json();
    await console.log(responseData);

    if(responseData.name){
        const loginSpan = document.querySelector("#loginSpan");
        loginSpan.innerHTML = `${responseData.name}`;

    }else{
        alert("이름이 없습니다");
    }

    if(responseData.msg){
        alert(responseData.msg);
    }








})