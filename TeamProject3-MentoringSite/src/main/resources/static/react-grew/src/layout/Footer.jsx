import "../css/styles.css";

export default function Footer() {

return(

    <footer className="footer">
  <div className="footer-container">
    <div className="footer-left">
      <p>&copy; 2024 MyCompany. All rights reserved.</p>
    </div>
    <div className="footer-center">
      <ul>
        <li><a href="#">About Us</a></li>
        <li><a href="#">Privacy Policy</a></li>
        <li><a href="#">Terms of Service</a></li>
      </ul>
    </div>
  </div>
</footer>
)
}