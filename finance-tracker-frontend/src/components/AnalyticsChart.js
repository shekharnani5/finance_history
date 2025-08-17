import React from 'react';
import { PieChart, Pie, Cell, Tooltip, Legend } from 'recharts';

const COLORS = ['#0088FE', '#00C49F', '#FFBB28', '#FF8042', '#AF19FF'];

export default function AnalyticsChart({ analytics }) {
    // Convert category totals to chart data
    const data = Object.entries(analytics.totalByCategory).map(([key, value]) => ({
        name: key,
        value,
    }));

    return (
        <div style={{ marginTop: '30px' }}>
            <h3>Expenses by Category</h3>
            <PieChart width={400} height={400}>
                <Pie data={data} dataKey="value" nameKey="name" outerRadius={150} fill="#8884d8" label>
                    {data.map((entry, index) => (
                        <Cell key={`cell-${index}`} fill={COLORS[index % COLORS.length]} />
                    ))}
                </Pie>
                <Tooltip />
                <Legend />
            </PieChart>
        </div>
    );
}
