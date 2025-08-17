import React, { useEffect, useState } from 'react';
import { getTransactions, addTransaction, getAnalytics } from '../services/api';
import TransactionForm from './TransactionForm';
import TransactionTable from './TransactionTable';
import AnalyticsChart from './AnalyticsChart';


export default function Dashboard() {
    const [transactions, setTransactions] = useState([]);
    const [analytics, setAnalytics] = useState(null);

    const fetchData = async () => {
        const [transRes, analyticsRes] = await Promise.all([getTransactions(), getAnalytics()]);
        setTransactions(transRes.data);
        setAnalytics(analyticsRes.data);
    };

    useEffect(() => {
        fetchData();
    }, []);

    const handleAddTransaction = async (transaction) => {
        await addTransaction(transaction);
        fetchData();
    };

    return (
        <div>
            <h1>Finance Tracker Dashboard</h1>
            <TransactionForm onAdd={handleAddTransaction} />
            <TransactionTable transactions={transactions} />
            {analytics && <AnalyticsChart analytics={analytics} />}
        </div>
    );
}
