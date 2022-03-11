<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
@import url('https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap');
 
     * {
           font-family : "Poppins";
     }
/* Style the navigation bar */
.navbar {
  width: 100%;
  background-color: #555;
  overflow: auto;
}

/* Navbar links */
.navbar a {
  float: left;
  text-align: center;
  padding: 12px;
  color: white;
  text-decoration: none;
  font-size: 17px;
}

/* Navbar links on mouse-over */
.navbar a:hover {
  background-color: blue;
}

/* Current/active navbar link */
.active {
  background-color: #04AA6D;
}
.danger {
background-color: red;
}
.dark {
background-color: black;
}


/* Add responsiveness - will automatically display the navbar vertically instead of horizontally on screens less than 500 pixels */
@media screen and (max-width: 500px) {
  .navbar a {
    float: none;
    display: block;
  }
}
</style>
<div class="navbar">
    
    <a class="active" href="<s:url action=""/>"><i class="fa fa-fw fa-home"></i> Home</a>
    <a href="<s:url action="depositAction"/>">Deposit</a>
    <a href="<s:url action="withdrawAction"/>">Withdraw</a>
    <a href="<s:url action="transferAction"/>">transfer</a>
    <a href="<s:url action="changePassword"/>">Change Password</a>
    <a href="<s:url action="showTxnAction"/>">Transactions</a>
    <a class="danger" href="<s:url action="deleteUserAction"/>">Delete Account</a>
    <a class="dark" href="<s:url action="logOutAction"/>">Log Out</a>

</div>