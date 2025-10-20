import React from "react";
import Navbar from "../common/Navbar";
import Footer from "../common/Footer";

export default function MainLayout({ children }) {
  return (
    <div className="main-layout">
      <Navbar />
      <main>{children}</main>
      <Footer />
    </div>
  );
}
