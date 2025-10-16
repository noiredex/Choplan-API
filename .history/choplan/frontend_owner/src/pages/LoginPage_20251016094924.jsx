// 로그인 페이지

import { useState } from "react";
import { useAuth } from "../hooks/useAuth";
import { ownerLogin } from "../api/ownerApi";

export default function LoginPage() {
    const { setToken } = useAuth();
    
}