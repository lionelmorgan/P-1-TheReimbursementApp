
window.addEventListener("load", async () => {
    let response = await fetch("http://localhost:9000/api/check-session");
    let result = await response.json();

    if(result.user_role_id == 1){
       window.location.href = `manager-dashboard`
    }else if(result.user_role_id == 2){
        window.location.href = `employee-dashboard`
    // }else{
    //     window.location.href = "../"
    // }
    }
})

async function login(e){
    e.preventDefault(); //this prevents form onsubmit from refreshing

    let usernameInputElem = document.getElementById("username-input");
    let passwordInputElem = document.getElementById("password-input");
    let roleInputElem = document.getElementById("role-input");

    let response = await fetch("http://localhost:9000/api/login",{
        method: "POST",
        body: JSON.stringify({
            username: usernameInputElem.value,
            password: passwordInputElem.value,
            user_role_id: roleInputElem.value

        })
    })

    let result = await response.json();

    if(result.data.user_role_id != 1){
        window.location.href = `employee-dashboard`
    }else{
        window.location.href = `manager-dashboard`
    }

}