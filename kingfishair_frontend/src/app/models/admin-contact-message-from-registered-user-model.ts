export interface AdminContactMessageFromRegisteredUserModel {

  messageId: number;
  userId: number;
  userEmail: string;
  userFullName: string;
  message: string;
  messageCategory: string;
}
