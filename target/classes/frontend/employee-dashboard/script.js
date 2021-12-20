let domain = "http://localhost:9000";

window.addEventListener("load", async () => {
    let response = await fetch("http://localhost:9000/api/check-session");
    let result = await response.json();

    //have to change this, so that if you go to employee-dashboard it is found
    if(!result.successful){
        window.location.href = "../"
    }
    else if(result.data.user_role_id === 1){
        window.location.href = "./manager-dashboard"
    }

    //add username and role id to screen
    let usernameElem = document.createElement("h2");
    usernameElem.innerText = "Welcome, " + result.data.username;


    let roleElem = document.createElement("h3");
    roleElem.innerText = "ID: " + result.data.user_role_id;

    let userInfo = document.getElementById("user-info");
    userInfo.appendChild(usernameElem);
    //userInfo.appendChild(roleElem);


    getEmployeeReimbursements();
    

})

// let domain = "http://localhost:9000";

// window.addEventListener("load", () => {
//     getEmployeeReimbursements();
// })

async function getEmployeeReimbursements(){


    //fetch reimbursements by id from backend
    //PULL ALL REIMBURSEMENTS FOR THIS EMPLOYEE. CHECK ENDPOINT
    let id = reimbursement.reimb_id
    //let response = await fetch(`${domain}/employee-dashboard/reimbursements/{id}`)
    let response = await fetch(`${domain}/employee-dashboard/reimbursements/5`)
    let reimbursement = await response.json();

    // data.sort((a,b) => {
    //     if(a.id > b.id)
    //         return -1;
    //     else return 1;
    // })

    //clear container
    let reimbursementContainer = document.getElementById("reimbursement-container");
    reimbursementContainer.innerHTML = "";

    //loop through each reimbursement and create the dom elements for the reimbursement
    // data.forEach(reimbursement => {
        // for(reimbursement in data){
        
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
            <button class="btn btn-danger" id="delete-btn-${reimbursement.reimb_id}" onclick="deleteReimbursement(event)">Delete</button>
        </div>
        `

        reimbursementContainer.appendChild(reimbursementItemElem);
    // }
    // );

    //append newly created element to the container
}

async function createReimbursement(e){
    e.preventDefault(); //stop form from automatically refreshing the page

    //get the value from the description input
   
    let amountInputElem = document.getElementById("amount-input");
    let amount = amountInputElem.value;
   
    let descriptionInputElem = document.getElementById("description-input");
    let description = descriptionInputElem.value;
    
    // let receiptInputElem = document.getElementById("receipt-input");
    // let receipt = receiptInputElem.value;

    let authorInputElem = document.getElementById("author-input");
    let author = authorInputElem.value;

    let resolverInputElem = document.getElementById("resolver-input");
    let resolver = resolverInputElem.value;

    // let statusIdInputElem = document.getElementById("status-id-input");
    // let statusId = statusIdInputElem.value;

    // let typeIdInputElem = document.getElementById("type-id-input");
    // let typeId = typeIdInputElem.value;

 //send post request with description in the body

// let id = reimbursement.reimb_id;
 await fetch(`${domain}/reimbursements/5`,{
    method: "POST",
    body: JSON.stringify({
        reimb_amount: amount,
        reimb_description: description,
        // reimb_receipt: receipt,
        reimb_author: author,
        reimb_resolver: resolver
        // reimb_status_id: statusId,
        // reimb_type_id: typeId
    })
})

amountInputElem.value = "";
descriptionInputElem.value = "";
// receiptInputElem.value = "";
authorInputElem.value = "";
resolverInputElem.value = "";
// statusIdInputElem.value = "";
// typeIdInputElem.value = "";

getEmployeeReimbursements();
}

async function deleteReimbursement(e){

// get id of reimbursement to deny
let id = e.target.id.slice("delete-btn-".length,e.target.id.length)
//send request to delete reimbursement
await fetch(`${domain}/reimbursements/${id}`,{
    method: "DELETE",
    body: JSON.stringify({
        reimb_id: id
    })

})

    
getEmployeeReimbursements();

}


async function login(e){
    e.preventDefault();

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

    if(result.data.user_role_id === 1){
        window.location.href = "./manager-dashboard";
    }else{
        window.location.href = "../";
    }

 
}