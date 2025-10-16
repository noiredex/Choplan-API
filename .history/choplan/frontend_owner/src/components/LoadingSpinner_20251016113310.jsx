export default function LoadingSpinner({ text = "로딩 중..." }) {
  return (
    <div className="spinner-container">
      <div className="spinner"></div>
      <p>{text}</p>
    </div>
  );
}
