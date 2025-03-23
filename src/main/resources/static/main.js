// const baseUrl = 'http://localhost:8080/api/admin';
//
// async function fetchUsers() {
//     try {
//         const response = await fetch(baseUrl);
//         if (!response.ok) {
//             throw new Error('Failed to fetch users');
//         }
//         const users = await response.json();
//         displayUsers(users);
//     } catch (error) {
//         console.error('Error:', error);
//     }
// }
//
// // Функция для отображения пользователей в таблице
// function displayUsers(users) {
//     const tableBody = document.querySelector('#usersList tbody');
//     tableBody.innerHTML = ''; // Очищаем текущие данные
//
//
//     users.forEach(user => {
//         const row = `
//                     <tr>
//                         <td>${user.id}</td>
//                         <td>${user.firstName}</td>
//                         <td>${user.lastName}</td>
//                         <td>${user.email}</td>
//                         <td>${user.password}</td>
//                         // <td>${user.roles.join(', ')}</td>
//
//                         <td>
//                             <button class="btn btn-sm btn-warning edit-btn" data-id="${user.id}" data-bs-toggle="modal" data-bs-target="#editModal">Edit</button>
//                             <button class="btn btn-sm btn-danger delete-btn" data-id="${user.id}">Delete</button>
//                         </td>
//                     </tr>
//                 `;
//         tableBody.insertAdjacentHTML('beforeend', row);
//     });
//
//
//     // document.getElementById('usersTable').style.display = 'table';
//     fetchUsers();
// }