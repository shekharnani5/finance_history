import React, { useState } from 'react';

export default function TransactionForm({ onAdd }) {
    const [type, setType] = useState('INCOME');
    const [category, setCategory] = useState('');
    const [amount, setAmount] = useState('');
    const [description, setDescription] = useState('');

    const handleSubmit = (e) => {
        e.preventDefault();
        if (!category || !amount) return;

        onAdd({
            type,
            category,
            amount: parseFloat(amount),
            description,
            transactionDate: new Date().toISOString().slice(0, 10), // today’s date in YYYY-MM-DD
        });

        setCategory('');
        setAmount('');
        setDescription('');
    };

    return (
        <form onSubmit={handleSubmit}>
            <h3>Add Transaction</h3>
            <select value={type} onChange={e => setType(e.target.value)}>
                <option value="INCOME">Income</option>
                <option value="EXPENSE">Expense</option>
            </select>
            <input
                placeholder="Category"
                value={category}
                onChange={e => setCategory(e.target.value)}
                required
            />
            <input
                placeholder="Amount"
                type="number"
                value={amount}
                onChange={e => setAmount(e.target.value)}
                required
            />
            <input
                placeholder="Description"
                value={description}
                onChange={e => setDescription(e.target.value)}
            />
            <button type="submit">Add</button>
        </form>
    );
}
