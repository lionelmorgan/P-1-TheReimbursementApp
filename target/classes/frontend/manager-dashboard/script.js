let domain = "http://localhost:9000";

window.addEventListener("load", () => {
    populateReimbursements();
})

async function populateReimbursements(){


    //fetch reimbursements from backend
    let response = await fetch(`${domain}/manager-dashboard/reimbursements`)
    let data = await response.json();

    data.sort((a,b) => {
        if(a.id > b.id)
            return -1;
        else return 1;
    })

    //clear container
    let reimbursementContainer = document.getElementById("reimbursement-container");
    reimbursementContainer.innerHTML = "";

    //loop through each reimbursement and create the dom elements for the reimbursement
    data.forEach(reimbursement => {
        let reimbursementItemElem = document.createElement("div");
        reimbursementItemElem.className = "reimbursement-item";
        reimbursementItemElem.id = reimbursement.reimb_id;

        reimbursementItemElem.innerHTML = `
        <div class="reimbursement-info">
            <span class="reimbursement-amount">$${reimbursement.reimb_amount}</span>
            <span class="reimbursement-description">${reimbursement.reimb_description}</span>
            ${(reimbursement.reimb_status_id == 1) ? '<span class="reimbursement-status green">Approved</span>' : '<span class="reimbursement-status red">Pending...</span>'}
        </div>
        <div class="reimbursement-btns">
            ${(reimbursement.reimb_status_id == 1) ? '' : `<button class="btn btn-success" id="approve-btn-${reimbursement.reimb_id}" onclick="approveReimbursement(event)">Approve</button>`}
            <button class="btn btn-danger" id="deny-btn-${reimbursement.reimb_id}" onclick="denyReimbursement(event)">Deny</button>
        </div>
        `

        reimbursementContainer.appendChild(reimbursementItemElem);
    });

    //append newly created element to the container
}

window.onload = async () => {
    let response = await fetch("http://localhost:9000/api/check-session");
    let result = await response.json();

    //go to login if no session is found
    if(!result.successful)
        window.location.href = "../";

    //go to sales dashboard if role is sales
    if(result.data.user_role_id === 2)
        window.location.href = "../employee-dashboard"

    //add username and role id to screen
    let usernameElem = document.createElement("h2");
    usernameElem.innerText = "Welcome, " + result.data.username;


    let roleElem = document.createElement("h3");
    roleElem.innerText = "ID: " + result.data.user_role_id;

    let userInfo = document.getElementById("user-info");
    userInfo.appendChild(usernameElem);
    //userInfo.appendChild(roleElem);

// //Reimbursement stuff

// let domain = "http://localhost:9000";

// window.addEventListener("load", () => {
//     populateReimbursements();
// })
}



    async function approveReimbursement(e){
        // get id of reimbursement to approve
        let id = e.target.id.slice("approve-btn-".length,e.target.id.length)
        //send request to approve reimbursement
        await fetch(`${domain}/reimbursements/${id}`,{
            method: "PATCH"
        })
    
        populateReimbursements();
    
    }
    
    async function denyReimbursement(e){
    
       // get id of reimbursement to deny
       let id = e.target.id.slice("deny-btn-".length,e.target.id.length)
       //send request to deny reimbursement
       await fetch(`${domain}/reimbursements/${id}`,{
           method: "DELETE",
           body: JSON.stringify({
               reimb_id: id
           })
       })
    
       populateReimbursements();
    
//         async function login(e){
//             e.preventDefault();
    
//             let usernameInputElem = document.getElementById("username-input");
//             let passwordInputElem = document.getElementById("password-input");
    
//             let response = await fetch("http://localhost:9000/api/login",{
//                 method: "POST",
//                 body: JSON.stringify({
//                     username: usernameInputElem.value,
//                     password: passwordInputElem.value
//                 })
//             })
    
//             let result = await response.json();
    
//             if(!result.successful){
//                 //display alert
//             }
    
//             window.location.href = `${result.data.user_role_id}-dashboard`
    
//             if(result.data.user_role_id === 1){
//                 window.location.href = "./manager-dashboard";
//             }else{
//                 window.location.href = "../";
//             }
    
    
//         }
//     }
}