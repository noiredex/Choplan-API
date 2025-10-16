import { useContext } from "react";
import { AuthCOntext } from "../contexts/AuthContext";

export const useAuth = () => {
    return useContext(AuthContext);
};