export function isNotAuthenticated(): boolean {
  return localStorage.getItem('user') == null;
}
