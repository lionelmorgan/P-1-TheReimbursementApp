async function logout(){
    await fetch("http://localhost:9000/api/logout", {
        method: "DELETE"
    })

    window.location.href = "../"
}