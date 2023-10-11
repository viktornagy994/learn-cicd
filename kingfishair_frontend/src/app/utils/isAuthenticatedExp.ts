export function isAuthenticatedExp(): boolean {
  return localStorage.getItem('user') != null;
}
