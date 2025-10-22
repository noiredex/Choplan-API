import React, { useState } from "react";
import "../../styles/SearchBar.css";

export default function SearchBar({ onSearch }) {
  const [query, setQuery] = useState("");

  const handleInputChange = (e) => {
    setQuery(e.target.value);
  };

  const handleSearch = (e) => {
    e.preventDefault();
    onSearch(query);
  };

  return (
    <form className="search-bar" onSubmit={handleSearch}>
      <input
        type="text"
        placeholder="매장명 또는 업종을 검색하세요"
        value={query}
        onChange={handleInputChange}
      />
      <button type="submit">검색</button>
    </form>
  );
}
