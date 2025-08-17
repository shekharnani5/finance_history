import React from 'react';

export default function TransactionTable({ transactions }) {
    return (
        <table border="1" cellPadding="5" style={{ marginTop: '20px' }}>
            <thead>
            <tr>
                <th>Type</th>
                <th>Category</th>
                <th>Amount</th>
                <th>Date</th>
                <th>Description</th>
            </tr>
            </thead>
            <tbody>
            {transactions.map(t => (
                <tr key={t.id}>
                    <td>{t.type}</td>
                    <td>{t.category}</td>
                    <td>{t.amount}</td>
                    <td>{new Date(t.transactionDate).toLocaleDateString()}</td>
                    <td>{t.description}</td>
                </tr>
            ))}
            </tbody>
        </table>
    );
}
