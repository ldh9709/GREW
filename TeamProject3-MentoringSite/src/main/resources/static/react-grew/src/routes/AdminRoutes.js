import { Routes, Route } from "react-router-dom";
import Admin from "../component/admin/Admin.jsx";

function AdminRoutes() {
    return (
      <Routes>
        {/* 어드민 라우트 보호 */}
        <Route
          path="/"
          element={
              <Admin />
          }
        />
      </Routes>
    );
}

export default AdminRoutes;