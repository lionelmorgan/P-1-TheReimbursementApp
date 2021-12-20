
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
            <span class="reimbursement-description">${reimbursement.reimb_description}</span>
            ${reimbursement.approve ? '<span class="reimbursement-status green">Approve</span>' : '<span class="reimbursement-status red">Pending</span>'}
        </div>
        <div class="reimbursement-btns">
            ${reimbursement.approve ? '' : `<button class="btn btn-success" id="approve-btn-${reimbursement.reimb_id}" onclick="approveReimbursement(event)">Approve</button>`}
            <button class="btn btn-danger" id="deny-btn-${reimbursement.reimb_id}" onclick="denyReimbursement(event)">Deny</button>
        </div>
        `

        reimbursementContainer.appendChild(reimbursementItemElem);
    });

    //append newly created element to the container
}

async function createReimbursement(e){
    e.preventDefault(); //stop form from automatically refreshing the page

    //get the value from the description input
   
    let amountInputElem = document.getElementById("amount-input");
    let amount = amountInputElem.value;
   
    let descriptionInputElem = document.getElementById("description-input");
    let description = descriptionInputElem.value;
    
    let receiptInputElem = document.getElementById("receipt-input");
    let receipt = receiptInputElem.value;

    let authorInputElem = document.getElementById("author-input");
    let author = authorInputElem.value;

    let resolverInputElem = document.getElementById("resolver-input");
    let resolver = resolverInputElem.value;

    let statusIdInputElem = document.getElementById("status-id-input");
    let statusId = statusIdInputElem.value;

    let typeIdInputElem = document.getElementById("type-id-input");
    let typeId = typeIdInputElem.value;
    
    //send post request with description in the body
    await fetch(`${domain}/reimbursements`,{
        method: "POST",
        body: JSON.stringify({
            reimb_amount: amount,
            reimb_description: description,
            reimb_receipt: receipt,
            reimb_author: author,
            reimb_resolver: resolver,
            reimb_status_id: statusId,
            reimb_type_id: typeId
        })
    })

    amountInputElem.value = "";
    descriptionInputElem.value = "";
    receiptInputElem.value = "";
    authorInputElem.value = "";
    resolverInputElem.value = "";
    statusIdInputElem.value = "";
    typeIdInputElem.value = "";

    populateReimbursements();
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
       method: "DELETE"
   })

   populateReimbursements();

    async function login(e){
        e.preventDefault();

        let usernameInputElem = document.getElementById("username-input");
        let passwordInputElem = document.getElementById("password-input");

        let response = await fetch("http://localhost:9000/api/login",{
            method: "POST",
            body: JSON.stringify({
                username: usernameInputElem.value,
                password: passwordInputElem.value
            })
        })

        let result = await response.json();

        if(!result.successful){
            //display alert
        }

        window.location.href = `${result.data.user_role_id}-dashboard`

        if(result.data.user_role_id === 1){
            window.location.href = "./manager-dashboard";
        }else{
            window.location.href = "../";
        }


    }
} 