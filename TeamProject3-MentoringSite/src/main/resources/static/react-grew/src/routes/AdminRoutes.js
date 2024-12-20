import { Routes, Route } from "react-router-dom";
import { AdminReportPage } from "../component/Admin/AdminReportPage";
import Admin from "../component/Admin/Admin";

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